package com.opsDashboard.merchant;

import com.opsDashboard.utils.Country;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
class MerchantInitializer implements ApplicationListener<ContextRefreshedEvent>
{
    private final MerchantRepository merchantRepo;

    MerchantInitializer(final MerchantRepository merchantRepo)
    {
        this.merchantRepo = merchantRepo;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event)
    {
        this.merchantRepo.save(new Merchant(
                Country.EE
                , "email"
                , "Mirek"
                , "MirPol"
                ,"phone"
        ));
    }
}
