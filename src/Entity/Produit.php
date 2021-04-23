<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Produit
 *
 * @ORM\Table(name="produit", indexes={@ORM\Index(name="IDX_29A5EC27BCF5E72D", columns={"categorie_id"})})
 * @ORM\Entity(repositoryClass="App\Repository\ProduitRepository")
 */
class Produit
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255, nullable=false)
     * @Assert\NotBlank(message="Veuillez saisir le nom ! ")
     */
    //@assert\length(min="4", max="8",
    //      minMessage="doit min {{ limit }}"
    //      maxMessage="doit max {{ limit }}")
    private $nom;

    /**
     * @var float
     *
     * @ORM\Column(name="prix", type="float", precision=10, scale=0, nullable=false)
     * @Assert\Positive(message="le prix {{ value }} est non valide ! ")
     * @Assert\Type(
     *     type="float",
     *     message="le prix doit être un float ! ")
     */
    private $prix;

    /**
     * @var string
     *
     * @ORM\Column(name="photo", type="string", length=255, nullable=false)
     */
//  * @Assert\File(
//  *     mimeTypes={"image/jpeg","image/gif","image/png"},
//  *     mimeTypesMessage = "Svp inserer une image valide (png,jpg,jpeg) !")
    private $photo;

    /**
     * @var string
     *
     * @ORM\Column(name="stock", type="string", length=50, nullable=false)
     * @Assert\Choice(
     *     choices={"Disponible","Indisponible"},
     *     message="Disponible ou Indisponible")
     */
    private $stock;

    /**
     *
     * @ORM\ManyToOne(targetEntity="Categorie")
     * @ORM\JoinColumn(name="categorie_id", referencedColumnName="id")
     */
    private $categorie;

    /**
     * @return int
     */
    public function getId(): int
    {
        return $this->id;
    }

    /**
     * @param int $id
     */
    public function setId(int $id): void
    {
        $this->id = $id;
    }

    /**
     * @return string
     */
    public function getNom(): ?string
    {
        return $this->nom;
    }

    /**
     * @param string $nom
     */
    public function setNom(string $nom): void
    {
        $this->nom = $nom;
    }

    /**
     * @return float
     */
    public function getPrix(): ?float
    {
        return $this->prix;
    }

    /**
     * @param float $prix
     */
    public function setPrix(float $prix): void
    {
        $this->prix = $prix;
    }

    /**
     * @return string
     */
    public function getPhoto(): ?string
    {
        return $this->photo;
    }

    /**
     * @param string $photo
     */
    public function setPhoto(string $photo): void
    {
        $this->photo = $photo;
    }

    /**
     * @return string
     */
    public function getStock(): ?string
    {
        return $this->stock;
    }

    /**
     * @param string $stock
     */
    public function setStock(string $stock): void
    {
        $this->stock = $stock;
    }

    /**
     * @return mixed
     */
    public function getCategorie()
    {
        return $this->categorie;
    }

    /**
     * @param mixed $categorie
     */
    public function setCategorie($categorie): void
    {
        $this->categorie = $categorie;
    }




}
