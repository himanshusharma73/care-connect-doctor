package org.careconnect.careconnectdoctor.repo;

import org.careconnect.careconnectcommon.entity.Checkup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckUpRepo extends JpaRepository<Checkup,Long> {

}
