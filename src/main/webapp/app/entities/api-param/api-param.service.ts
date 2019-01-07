import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IApiParam } from 'app/shared/model/api-param.model';

type EntityResponseType = HttpResponse<IApiParam>;
type EntityArrayResponseType = HttpResponse<IApiParam[]>;

@Injectable({ providedIn: 'root' })
export class ApiParamService {
    public resourceUrl = SERVER_API_URL + 'api/api-params';

    constructor(protected http: HttpClient) {}

    create(apiParam: IApiParam): Observable<EntityResponseType> {
        return this.http.post<IApiParam>(this.resourceUrl, apiParam, { observe: 'response' });
    }

    update(apiParam: IApiParam): Observable<EntityResponseType> {
        return this.http.put<IApiParam>(this.resourceUrl, apiParam, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IApiParam>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IApiParam[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
