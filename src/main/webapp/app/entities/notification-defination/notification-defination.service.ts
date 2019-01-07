import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INotificationDefination } from 'app/shared/model/notification-defination.model';

type EntityResponseType = HttpResponse<INotificationDefination>;
type EntityArrayResponseType = HttpResponse<INotificationDefination[]>;

@Injectable({ providedIn: 'root' })
export class NotificationDefinationService {
    public resourceUrl = SERVER_API_URL + 'api/notification-definations';

    constructor(protected http: HttpClient) {}

    create(notificationDefination: INotificationDefination): Observable<EntityResponseType> {
        return this.http.post<INotificationDefination>(this.resourceUrl, notificationDefination, { observe: 'response' });
    }

    update(notificationDefination: INotificationDefination): Observable<EntityResponseType> {
        return this.http.put<INotificationDefination>(this.resourceUrl, notificationDefination, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<INotificationDefination>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<INotificationDefination[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
