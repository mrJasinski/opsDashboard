package com.opsDashboard.merchant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlMerchantRepository extends MerchantRepository, JpaRepository<Merchant, Integer>
{
}
