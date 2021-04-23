<?php

namespace App\Controller\front;

use App\Entity\Produit;
use App\Entity\Categorie;
use App\Repository\ProduitRepository;
use App\Repository\CodepromoRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/front/produit")
 */
class ProduitController extends AbstractController
{
    /**
     * @Route("/", name="produit_front_index", methods={"GET"})
     */
    public function index(): Response
    {
        $produits = $this->getDoctrine()
            ->getRepository(Produit::class)
            ->findAll();

        return $this->render('frontend/indexProduit.html.twig', [
            'produits' => $produits,'testCode'=>0
        ]);
    }

    /**
     * @Route("/recherche", name="rechercheF", methods={"POST"})
     */
    function recherche(ProduitRepository $repository, Request $request){
        $nom=$request->get('search');
        $produits=$repository->Search($nom);
        return $this->render('frontend/indexProduit.html.twig', [
            'produits' => $produits,'testCode'=>0
        ]);
    }

    /**
     * @param ProduitRepository $repository
     * @return Response
     * @Route("Produit/TriePrix", name="triePrix")
     */
    function TriePrix(ProduitRepository $repository) {
        $produits=$repository->OrderByPrixDQL();
        return $this->render('frontend/indexProduit.html.twig', [
            'produits' => $produits,'testCode'=>0
        ]);
    }


    /**
     * @param Request $request
     * @param CodepromoRepository $repository
     * @return false|Response
     * @Route("/Codepromo", name="Codepromo", methods={"POST"})
     */
    function Codepromo(Request $request, CodepromoRepository $repository)
    {
        $testCode = 0;
        $code=$request->get('code');
        $em = $this->getDoctrine()->getManager();
        $produits = $this->getDoctrine()
            ->getRepository(Produit::class)
            ->findAll();
        if ($repository->VerifierCode($code) != NULL) {
           /* $query = $em->createQuery(
                'SELECT p.id, p.nom, p.prix-(p.prix*0.2), p.photo, c.nom, p.stock
                FROM App\Entity\Produit p INNER JOIN App\Entity\Categorie c WHERE p.categorie = c.nom'
            );*/

            $testCode = 1;
        }else {
            $testCode=2;

        }

       /* $rep = $query->getResult();*/
        return $this->render('frontend/indexProduit.html.twig',
            ['produits' => $produits, 'testCode'=>$testCode]);

    }

}
