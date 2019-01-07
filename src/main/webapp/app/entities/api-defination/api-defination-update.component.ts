import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IApiDefination } from 'app/shared/model/api-defination.model';
import { ApiDefinationService } from './api-defination.service';
import { INotificationDefination } from 'app/shared/model/notification-defination.model';
import { NotificationDefinationService } from 'app/entities/notification-defination';

@Component({
    selector: 'jhi-api-defination-update',
    templateUrl: './api-defination-update.component.html'
})
export class ApiDefinationUpdateComponent implements OnInit {
    apiDefination: IApiDefination;
    isSaving: boolean;

    notificationdefinations: INotificationDefination[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected apiDefinationService: ApiDefinationService,
        protected notificationDefinationService: NotificationDefinationService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ apiDefination }) => {
            this.apiDefination = apiDefination;
        });
        this.notificationDefinationService.query().subscribe(
            (res: HttpResponse<INotificationDefination[]>) => {
                this.notificationdefinations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackNotificationDefinationById(index: number, item: INotificationDefination) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
