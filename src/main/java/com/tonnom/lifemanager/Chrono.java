package com.tonnom.lifemanager;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Chrono {

    @FXML private Label chronoLabel;
    private Timeline rafraichisseurVisuel; // Boucle locale d'affichage

    // Cette méthode s'exécute automatiquement à chaque fois que l'onglet s'affiche
    @FXML
    public void initialize() {
        // 1. On affiche direct la valeur actuelle (ex: si on revient d'un autre onglet)
        mettreAJourLeTexte();

        // 2. Si le moteur tourne, on lance la boucle qui rafraîchit le Label
        if (TimeManager.getInstance().estEnMarche()) {
            lancerBoucleAffichage();
        }
    }

    @FXML
    private void handleStart() {
        // On donne l'ordre au moteur de démarrer
        TimeManager.getInstance().demarrer();
        // On lance la mise à jour visuelle du Label
        lancerBoucleAffichage();
    }

    private void lancerBoucleAffichage() {
        if (rafraichisseurVisuel != null) rafraichisseurVisuel.stop();

        // On crée une petite boucle qui demande l'heure au moteur 2 fois par seconde
        rafraichisseurVisuel = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            mettreAJourLeTexte();
        }));
        rafraichisseurVisuel.setCycleCount(Animation.INDEFINITE);
        rafraichisseurVisuel.play();
    }

    private void mettreAJourLeTexte() {
        long totalSecs = TimeManager.getInstance().getSecondesEcoulees();
        long mins = totalSecs / 60;
        long secs = totalSecs % 60;
        // On met à jour le Label sur l'écran
        chronoLabel.setText(String.format("%d : %02d", mins, secs));
    }
}
/*
Catégorie,Méthode,Action
Création,Instant.now(),Obtient l'instant actuel.
,Instant.ofEpochMilli(long),Instancie à partir des millisecondes écoulées depuis le 1er janvier 1970.
,Instant.ofEpochSecond(long),Instancie à partir des secondes écoulées depuis le 1er janvier 1970.
,Instant.parse(CharSequence),"Instancie à partir d'une chaîne au format ISO-8601 (ex: ""2026-03-06T10:00:00Z"")."
Comparaison,isAfter(Instant),Vérifie la postériorité par rapport à un autre instant.
,isBefore(Instant),Vérifie l'antériorité par rapport à un autre instant.
,equals(Object),Vérifie l'égalité avec un autre instant.
Manipulation,plusMillis(long),Ajoute des millisecondes.
,plusSeconds(long),Ajoute des secondes.
,minusMillis(long),Soustrait des millisecondes.
,minusSeconds(long),Soustrait des secondes.
Extraction,toEpochMilli(),Retourne le nombre total de millisecondes depuis l'époque.
,getEpochSecond(),Retourne le nombre total de secondes depuis l'époque.
*/