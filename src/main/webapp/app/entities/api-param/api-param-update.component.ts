import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IApiParam } from 'app/shared/model/api-param.model';
import { ApiParamService } from './api-param.service';
import { IApiDefination } from 'app/shared/model/api-defination.model';
import { ApiDefinationService } from 'app/entities/api-defination';

@Component({
    selector: 'jhi-api-param-update',
    templateUrl: './api-param-update.component.html'
})
export class ApiParamUpdateComponent implements OnInit {
    apiParam: IApiParam;
    isSaving: boolean;

    apidefinations: IApiDefination[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected apiParamService: ApiParamService,
        protected apiDefinationService: ApiDefinationService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ apiParam }) => {
            this.apiParam = apiParam;
        });
        this.apiDefinationService.query().subscribe(
            (res: HttpResponse<IApiDefination[]>) => {
                this.apidefinations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.apiParam.id !== undefined) {
            this.subscribeToSaveResponse(this.apiParamService.update(this.apiParam));
        } else {
            this.subscribeToSaveResponse(this.apiParamService.create(this.apiParam));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IApiParam>>) {
        result.subscribe((res: HttpResponse<IApiParam>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackApiDefinationById(index: number, item: IApiDefination) {
        return item.id;
    }
}
