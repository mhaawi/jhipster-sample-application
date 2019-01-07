import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { NotificationDefination } from 'app/shared/model/notification-defination.model';
import { NotificationDefinationService } from './notification-defination.service';
import { NotificationDefinationComponent } from './notification-defination.component';
import { NotificationDefinationDetailComponent } from './notification-defination-detail.component';
import { NotificationDefinationUpdateComponent } from './notification-defination-update.component';
import { NotificationDefinationDeletePopupComponent } from './notification-defination-delete-dialog.component';
import { INotificationDefination } from 'app/shared/model/notification-defination.model';

@Injectable({ providedIn: 'root' })
export class NotificationDefinationResolve implements Resolve<INotificationDefination> {
    constructor(private service: NotificationDefinationService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<NotificationDefination> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<NotificationDefination>) => response.ok),
                map((notificationDefination: HttpResponse<NotificationDefination>) => notificationDefination.body)
            );
        }
        return of(new NotificationDefination());
    }
}

export const notificationDefinationRoute: Routes = [
    {
        path: 'notification-defination',
        component: NotificationDefinationComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterSampleApplicationApp.notificationDefination.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'notification-defination/:id/view',
        component: NotificationDefinationDetailComponent,
        resolve: {
            notificationDefination: NotificationDefinationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.notificationDefination.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'notification-defination/new',
        component: NotificationDefinationUpdateComponent,
        resolve: {
            notificationDefination: NotificationDefinationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.notificationDefination.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'notification-defination/:id/edit',
        component: NotificationDefinationUpdateComponent,
        resolve: {
            notificationDefination: NotificationDefinationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.notificationDefination.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const notificationDefinationPopupRoute: Routes = [
    {
        path: 'notification-defination/:id/delete',
        component: NotificationDefinationDeletePopupComponent,
        resolve: {
            notificationDefination: NotificationDefinationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.notificationDefination.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
