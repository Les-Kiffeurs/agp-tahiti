package fr.cyu.depinfo.agp.tahiti.persistence.bde.hibernate;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.schema.TargetType;
import org.hibernate.tool.schema.internal.SchemaCreatorImpl;
import org.hibernate.tool.schema.internal.exec.GenerationTarget;
import org.hibernate.tool.schema.spi.SchemaManagementToolCoordinator;

import java.util.EnumSet;

public class HibernateDataInit {

    public static void createTables() {
        // Création du registre de services à partir du fichier de configuration hibernate.cfg.xml
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml") // Charge la configuration depuis hibernate.cfg.xml
                .build();

        // Création des métadonnées des entités à partir des sources
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        Metadata metadata = metadataSources.buildMetadata();

        // Initialisation de l'objet SchemaCreatorImpl pour créer le schéma
        SchemaCreatorImpl schemaCreator = new SchemaCreatorImpl(serviceRegistry);

        // Création des tables dans la base de données
        schemaCreator.doCreation(metadata, true);

        // Libération du registre des services pour éviter les fuites de ressources
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }
}
