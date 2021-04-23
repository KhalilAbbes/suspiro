<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Codepromo
 *
 * @ORM\Table(name="codepromo")
 * @ORM\Entity(repositoryClass="App\Repository\CodepromoRepository")
 *@UniqueEntity(fields={"code"},
 *     message="Ce code existe déjà ! ")
 */
class Codepromo
{
    /**
     * @var int
     *
     * @ORM\Column(name="ID", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="Code", type="string", length=50, nullable=false)
     *@Assert\NotBlank(message="Veuillez saisir le code ! ")
     *@Assert\Length(min="4", max="15",
     *    minMessage="Le code doit être plus que {{ limit }} !",
     *    maxMessage="Le code doit être moins que {{ limit }}")
     */
    private $code;

    /**
     * @return int
     */
    public function getId(): ?int
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
    public function getCode(): ?string
    {
        return $this->code;
    }

    /**
     * @param string $code
     */
    public function setCode(string $code): void
    {
        $this->code = $code;
    }




}
