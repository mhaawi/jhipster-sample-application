import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IApiDefination } from 'app/shared/model/api-defination.model';

type EntityResponseType = HttpResponse<IApiDefination>;
type EntityArrayResponseType = HttpResponse<IApiDefination[]>;

@Injectable({ providedIn: 'root' })
export class ApiDefinationService {
    public resourceUrl = SERVER_API_URL + 'api/api-definations';

    constructor(protected http: HttpClient) {}

    create(apiDefination: IApiDefination): Observable<EntityResponseType> {
        return this.http.post<IApiDefination>(this.resourceUrl, apiDefination, { observe: 'response' });
    }

    update(apiDefination: IApiDefination): Observable<EntityResponseType> {
        return this.http.put<IApiDefination>(this.resourceUrl, apiDefination, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IApiDefination>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IApiDefination[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
