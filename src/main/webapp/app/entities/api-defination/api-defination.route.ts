import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ApiDefination } from 'app/shared/model/api-defination.model';
import { ApiDefinationService } from './api-defination.service';
import { ApiDefinationComponent } from './api-defination.component';
import { ApiDefinationDetailComponent } from './api-defination-detail.component';
import { ApiDefinationUpdateComponent } from './api-defination-update.component';
import { ApiDefinationDeletePopupComponent } from './api-defination-delete-dialog.component';
import { IApiDefination } from 'app/shared/model/api-defination.model';

@Injectable({ providedIn: 'root' })
export class ApiDefinationResolve implements Resolve<IApiDefination> {
    constructor(private service: ApiDefinationService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ApiDefination> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ApiDefination>) => response.ok),
                map((apiDefination: HttpResponse<ApiDefination>) => apiDefination.body)
            );
        }
        return of(new ApiDefination());
    }
}

export const apiDefinationRoute: Routes = [
    {
        path: 'api-defination',
        component: ApiDefinationComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterSampleApplicationApp.apiDefination.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'api-defination/:id/view',
        component: ApiDefinationDetailComponent,
        resolve: {
            apiDefination: ApiDefinationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.apiDefination.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'api-defination/new',
        component: ApiDefinationUpdateComponent,
        resolve: {
            apiDefination: ApiDefinationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.apiDefination.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'api-defination/:id/edit',
        component: ApiDefinationUpdateComponent,
        resolve: {
            apiDefination: ApiDefinationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.apiDefination.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const apiDefinationPopupRoute: Routes = [
    {
        path: 'api-defination/:id/delete',
        component: ApiDefinationDeletePopupComponent,
        resolve: {
            apiDefination: ApiDefinationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.apiDefination.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
