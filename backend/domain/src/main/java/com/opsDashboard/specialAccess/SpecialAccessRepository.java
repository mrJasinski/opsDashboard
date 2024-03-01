package com.opsDashboard.specialAccess;

import java.util.List;
import java.util.Optional;

interface SpecialAccessRepository
{
    SpecialAccess save(SpecialAccess toSave);

    List<SpecialAccess> findAll();

    Optional<SpecialAccess> findByStockId(String stockId);
}
