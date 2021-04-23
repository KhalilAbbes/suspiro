<?php

namespace App\Controller;

use App\Entity\Codepromo;
use App\Form\CodepromoType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/codepromo")
 */
class CodepromoController extends AbstractController
{
    /**
     * @Route("/", name="codepromo_index", methods={"GET"})
     */
    public function index(): Response
    {
        $codepromos = $this->getDoctrine()
            ->getRepository(Codepromo::class)
            ->findAll();

        return $this->render('codepromo/index.html.twig', [
            'codepromos' => $codepromos,
        ]);
    }

    /**
     * @Route("/new", name="codepromo_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $codepromo = new Codepromo();
        $form = $this->createForm(CodepromoType::class, $codepromo);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($codepromo);
            $entityManager->flush();

            return $this->redirectToRoute('codepromo_index');
        }

        return $this->render('codepromo/new.html.twig', [
            'codepromo' => $codepromo,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="codepromo_show", methods={"GET"})
     */
    public function show(Codepromo $codepromo): Response
    {
        return $this->render('codepromo/show.html.twig', [
            'codepromo' => $codepromo,
        ]);
    }

    /**
     * @Route("/{id}/edit", name="codepromo_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Codepromo $codepromo): Response
    {
        $form = $this->createForm(CodepromoType::class, $codepromo);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('codepromo_index');
        }

        return $this->render('codepromo/edit.html.twig', [
            'codepromo' => $codepromo,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="codepromo_delete", methods={"POST"})
     */
    public function delete(Request $request, Codepromo $codepromo): Response
    {
        if ($this->isCsrfTokenValid('delete'.$codepromo->getId(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($codepromo);
            $entityManager->flush();
        }

        return $this->redirectToRoute('codepromo_index');
    }
}
