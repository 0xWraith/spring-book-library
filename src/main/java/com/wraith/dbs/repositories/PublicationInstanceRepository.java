package com.wraith.dbs.repositories;

import com.wraith.dbs.models.PublicationInstance;
import com.wraith.dbs.utils.enums.EPublicationInstanceStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PublicationInstanceRepository extends JpaRepository<PublicationInstance, String>
{

    @Modifying
    @Transactional
    @Query("DELETE FROM PublicationInstance AS pi WHERE pi.publication_id = :publicationId")
    void deleteAllPublicationInstances(String publicationId);

    @Modifying
    @Transactional
    @Query("DELETE FROM PublicationInstance AS pi WHERE pi.id = :instanceId")
    void deletePublicationInstance(String instanceId);

    @Query("SELECT pi FROM PublicationInstance AS pi WHERE pi.publication_id = :publicationId AND pi.status = :status")
    ArrayList<PublicationInstance> findAvailablePublicationInstanceForRental(String publicationId, EPublicationInstanceStatus status);

    PublicationInstance findFirstById(String instanceId);
}
