import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { AppConstants } from "../../constans/app.constans";

@Injectable({ providedIn : 'root'})
export class ContactService
{
    constructor(private http: HttpClient)
    {

    }

    sendMessage(
        email: string
        , title : string
        , category : string
        , subCategory : string
        , stockId : string
        , content : string)
    {
        return this.http.post(AppConstants.APP_URL + AppConstants.CONTACT_SEND_URL
            , {
                email : email
                , title : title
                , category : category
                , subCategory : subCategory
                , stockId : stockId
                , content : content
            });
    }
}