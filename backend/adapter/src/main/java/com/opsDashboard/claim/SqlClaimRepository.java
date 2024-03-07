package com.opsDashboard.claim;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlClaimRepository extends ClaimRepository, JpaRepository<Claim, Integer>
{

}
