package com.opsDashboard.specialAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlSpecialAccessRepository extends SpecialAccessRepository, JpaRepository<SpecialAccess, Integer>
{

}
