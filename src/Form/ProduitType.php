<?php

namespace App\Form;

use App\Entity\Categorie;
use App\Entity\Produit;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ProduitType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom',TextType::class,[
                'label'=>'Nom',
                'attr'=>[
                    'placeholder'=>'Saisir le nom',
                    'class'=>'nom'
                ]
            ])

            ->add('prix',NumberType::class,[
                'label'=>'Prix',
                'attr'=>[
                    'placeholder'=>'Saisir le prix',
                    'class'=>'prix'
                ]
            ])

            ->add('photo',FileType::class,[
                'label'=>'Image produit',
                'mapped'=> false,
                'required' => false,
                'data_class' => null
            ])

            ->add('stock',TextType::class,[
                'label'=>'Stock',
                'attr'=>[
                    'placeholder'=>'Disponible/Indisponible',
                    'class'=>'stock'
                ]
            ])

            ->add('categorie',EntityType::class,[
                'class'=>Categorie::class,
                'choice_label'=>'nom'
            ])

        ;}

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Produit::class,
        ]);
    }
}