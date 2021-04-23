<?php

namespace App\Repository;

use App\Entity\Produit;
use App\Entity\Codepromo;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Produit|null find($id, $lockMode = null, $lockVersion = null)
 * @method Produit|null findOneBy(array $criteria, array $orderBy = null)
 * @method Produit[]    findAll()
 * @method Produit[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ProduitRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Produit::class);
    }

    // /**
    //  * @return Produit[] Returns an array of Produit objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('p.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Produit
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */

    public function OrderByPrixDQL() {
        $em=$this->getEntityManager();
        $query=$em->createQuery(
           'SELECT p From App\Entity\Produit p ORDER BY p.prix ASC');
        return $query->getResult();
    }


    function Search($nom) {
        return $this->createQueryBuilder('p')
            ->where('p.nom LIKE :nom')
            ->setParameter('nom','%'.$nom.'%')
            ->getQuery()->getResult();
    }



    /*public function OrderByPrixQB() {
        return $this->createQueryBuilder('p')
            ->orderBy('p.prix', 'ASC')
            ->getQuery()->getResult();
    }

    function OrderByCategorie($id) {
        return $this->createQueryBuilder('p')
            ->join('p.categorie', 'c')
            ->addSelect('c')
            ->where('c.id=id')
            ->setParameter('id',$id)
            ->getQuery()->getResult();
    }*/
}
