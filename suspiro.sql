-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 05 mars 2021 à 04:03
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `suspiro`
--

-- --------------------------------------------------------

--
-- Structure de la table `encheres`
--

CREATE TABLE `encheres` (
  `id_ench` int(10) NOT NULL,
  `label_prod` varchar(15) NOT NULL,
  `deb_inscri` datetime(6) NOT NULL,
  `fin_inscri` datetime(6) NOT NULL,
  `deb_vente` datetime(6) NOT NULL,
  `fin_vente` datetime(6) NOT NULL,
  `prix` float NOT NULL,
  `etat` varchar(10) NOT NULL,
  `id_part` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `encheres`
--

INSERT INTO `encheres` (`id_ench`, `label_prod`, `deb_inscri`, `fin_inscri`, `deb_vente`, `fin_vente`, `prix`, `etat`, `id_part`) VALUES
(91, 'biano', '2020-08-11 00:00:00.000000', '2020-08-11 00:00:00.000000', '2020-08-11 00:00:00.000000', '2020-08-11 00:00:00.000000', 10.5, '1', 101);

-- --------------------------------------------------------

--
-- Structure de la table `participants`
--

CREATE TABLE `participants` (
  `id_part` int(11) NOT NULL,
  `id_ench` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `resultat` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `participants`
--

INSERT INTO `participants` (`id_part`, `id_ench`, `id_user`, `resultat`) VALUES
(999, 14, 77, 'true');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `encheres`
--
ALTER TABLE `encheres`
  ADD PRIMARY KEY (`id_ench`);

--
-- Index pour la table `participants`
--
ALTER TABLE `participants`
  ADD PRIMARY KEY (`id_part`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `encheres`
--
ALTER TABLE `encheres`
  MODIFY `id_ench` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8832;

--
-- AUTO_INCREMENT pour la table `participants`
--
ALTER TABLE `participants`
  MODIFY `id_part` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1000;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
