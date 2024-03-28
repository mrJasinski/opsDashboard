import { Component } from "@angular/core";
import { NgForm } from '@angular/forms';
import { ContactService } from "./contact.service";

@Component({
    selector : 'contact-card'
    , templateUrl : './contact.component.html'
})
export class ContactComponent
{
    constructor(private contactService : ContactService)
    {
        
    }

    onSubmit(form: NgForm)
    {
        if (!form.valid)
            return;
    
        this.contactService.sendMessage(
            form.value.email
            , form.value.title
            , form.value.category
            , form.value.subCategory
            , form.value.stockId
            , form.value.content  
            ).subscribe(); 

        form.reset();
    }
}
