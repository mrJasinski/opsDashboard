package com.opsDashboard.claim;

import java.util.List;

interface ClaimRepository
{
    Claim save(Claim toSave);

    List<Claim> findAll();
}
