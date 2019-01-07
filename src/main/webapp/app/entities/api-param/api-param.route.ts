import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ApiParam } from 'app/shared/model/api-param.model';
import { ApiParamService } from './api-param.service';
import { ApiParamComponent } from './api-param.component';
import { ApiParamDetailComponent } from './api-param-detail.component';
import { ApiParamUpdateComponent } from './api-param-update.component';
import { ApiParamDeletePopupComponent } from './api-param-delete-dialog.component';
import { IApiParam } from 'app/shared/model/api-param.model';

@Injectable({ providedIn: 'root' })
export class ApiParamResolve implements Resolve<IApiParam> {
    constructor(private service: ApiParamService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ApiParam> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ApiParam>) => response.ok),
                map((apiParam: HttpResponse<ApiParam>) => apiParam.body)
            );
        }
        return of(new ApiParam());
    }
}

export const apiParamRoute: Routes = [
    {
        path: 'api-param',
        component: ApiParamComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterSampleApplicationApp.apiParam.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'api-param/:id/view',
        component: ApiParamDetailComponent,
        resolve: {
            apiParam: ApiParamResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.apiParam.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'api-param/new',
        component: ApiParamUpdateComponent,
        resolve: {
            apiParam: ApiParamResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.apiParam.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'api-param/:id/edit',
        component: ApiParamUpdateComponent,
        resolve: {
            apiParam: ApiParamResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.apiParam.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const apiParamPopupRoute: Routes = [
    {
        path: 'api-param/:id/delete',
        component: ApiParamDeletePopupComponent,
        resolve: {
            apiParam: ApiParamResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterSampleApplicationApp.apiParam.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
