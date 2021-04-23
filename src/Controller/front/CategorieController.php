<?php

namespace App\Controller\front;

use App\Entity\Categorie;
use App\Form\CategorieType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/front/categorie")
 */
class CategorieController extends AbstractController
{
    /**
     * @Route("/", name="categorie_front_index", methods={"GET"})
     */
    public function index(): Response
    {
        $categories = $this->getDoctrine()
            ->getRepository(Categorie::class)
            ->findAll();

        return $this->render('frontend/indexCategorie.html.twig', [
            'categories' => $categories,
        ]);
    }


}
