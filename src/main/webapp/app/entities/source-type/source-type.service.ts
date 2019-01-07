import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISourceType } from 'app/shared/model/source-type.model';

type EntityResponseType = HttpResponse<ISourceType>;
type EntityArrayResponseType = HttpResponse<ISourceType[]>;

@Injectable({ providedIn: 'root' })
export class SourceTypeService {
    public resourceUrl = SERVER_API_URL + 'api/source-types';

    constructor(protected http: HttpClient) {}

    create(sourceType: ISourceType): Observable<EntityResponseType> {
        return this.http.post<ISourceType>(this.resourceUrl, sourceType, { observe: 'response' });
    }

    update(sourceType: ISourceType): Observable<EntityResponseType> {
        return this.http.put<ISourceType>(this.resourceUrl, sourceType, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISourceType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISourceType[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
