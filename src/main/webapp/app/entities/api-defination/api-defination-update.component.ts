import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IApiDefination } from 'app/shared/model/api-defination.model';
import { ApiDefinationService } from './api-defination.service';

@Component({
    selector: 'jhi-api-defination-update',
    templateUrl: './api-defination-update.component.html'
})
export class ApiDefinationUpdateComponent implements OnInit {
    apiDefination: IApiDefination;
    isSaving: boolean;

    constructor(protected apiDefinationService: ApiDefinationService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ apiDefination }) => {
            this.apiDefination = apiDefination;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.apiDefination.id !== undefined) {
            this.subscribeToSaveResponse(this.apiDefinationService.update(this.apiDefination));
        } else {
            this.subscribeToSaveResponse(this.apiDefinationService.create(this.apiDefination));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IApiDefination>>) {
        result.subscribe((res: HttpResponse<IApiDefination>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
