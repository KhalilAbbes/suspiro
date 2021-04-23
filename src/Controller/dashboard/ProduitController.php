<?php

namespace App\Controller\dashboard;

use App\Entity\Produit;
use App\Entity\Codepromo;
use App\Form\ProduitType;
use App\Repository\ProduitRepository;
use phpDocumentor\Reflection\Types\True_;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Service\FileUploader;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\File\File;
use Knp\Component\Pager\PaginatorInterface;
use Dompdf\Dompdf;
use Dompdf\Options;

/**
 * @Route("/dashboard/produit")
 */
class ProduitController extends AbstractController
{
    /**
     * @Route("/", name="produit_dashboard_index", methods={"GET"})
     */
    public function index(): Response
    {
        $produits = $this->getDoctrine()
            ->getRepository(Produit::class)
            ->findAll();

        return $this->render('backend/indexProduit.html.twig', [
            'produits' => $produits,
        ]);
    }


    /**
     * @Route("/new", name="produit_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $produit = new Produit();
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $photo=$request->files->get('produit')['photo'];
            $uploads_directory=$this->getParameter('uploads_directory');
            //if (file_exists($uploads_directory)) {
             //   unlink($uploads_directory);
           // }
            $filename=md5(uniqid()) . '.' . $photo->guessExtension();
            $photo->move(
                $uploads_directory,
                $filename
            );

            $produit->setPhoto($filename);
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($produit);
            $entityManager->flush();

            return $this->redirectToRoute('produit_dashboard_index');
        }

        return $this->render('produit/new.html.twig', [
            'produit' => $produit,
            'form' => $form->createView(),
        ]);
    }


    /**
     * @Route("/{id}", name="produit_show", methods={"GET"})
     */
    public function show(Produit $produit): Response
    {
        return $this->render('produit/show.html.twig', [
            'produit' => $produit,
        ]);
    }

    /**
     * @Route("dashboard/{id}/edit", name="produit_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Produit $produit): Response
    {
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $photo=$request->files->get('produit')['photo'];
            $uploads_directory=$this->getParameter('uploads_directory');

            $filename=md5(uniqid()) . '.' . $photo->guessExtension();
            $photo->move(
                $uploads_directory,
                $filename
            );

            $produit->setPhoto($filename);
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('produit_dashboard_index');
        }

        return $this->render('produit/edit.html.twig', [
            'produit' => $produit,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="produit_delete", methods={"POST"})
     */
    public function delete(Request $request, Produit $produit): Response
    {
        if ($this->isCsrfTokenValid('delete'.$produit->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($produit);
            $entityManager->flush();
        }

        return $this->redirectToRoute('produit_dashboard_index');
    }

    /**
     * @Route("produit/recherche", name="recherche", methods={"POST"})
     */
    function recherche(ProduitRepository $repository, Request $request){
        $nom=$request->get('search');
        $produits=$repository->Search($nom);
        return $this->render('backend/indexProduit.html.twig', [
            'produits' => $produits,
        ]);
    }

    /*/**
     * @Route("produit/recherche", name="recherche", methods={"POST"})
     */
     /*function recherche(ProduitRepository $repository, Request $request){
        $data=$request->get('search');
        $produits=$repository->findBy(['nom'=>$data]);
        return $this->render('backend/indexProduit.html.twig', [
            'produits' => $produits,
        ]);
    }*/


    /*/**
     * @Route("/Codepromo", name="Codepromo", methods={"POST"})
     */
   /* function Codepromo(ProduitRepository $repository, Request $request){
        $code=$request->get('code');
        if ($repository->VerifierCode($code) != NULL) {
            return True;
        } else {
            return false;
        }
    }*/





    /*
    /**
     * @Route("/pdf", name="pdf", methods={"GET"})
     */
   /* public function pdf(EventsRepository $EventsRepository): Response
    {
        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('events/pdf.html.twig', [
            'events' => $EventsRepository->findAll(),
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (inline view)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => false
        ]);
    }*/
}
