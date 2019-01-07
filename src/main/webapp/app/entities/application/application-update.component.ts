import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IApplication } from 'app/shared/model/application.model';
import { ApplicationService } from './application.service';
import { INotificationDefination } from 'app/shared/model/notification-defination.model';
import { NotificationDefinationService } from 'app/entities/notification-defination';

@Component({
    selector: 'jhi-application-update',
    templateUrl: './application-update.component.html'
})
export class ApplicationUpdateComponent implements OnInit {
    application: IApplication;
    isSaving: boolean;

    notificationdefinations: INotificationDefination[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected applicationService: ApplicationService,
        protected notificationDefinationService: NotificationDefinationService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ application }) => {
            this.application = application;
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
        if (this.application.id !== undefined) {
            this.subscribeToSaveResponse(this.applicationService.update(this.application));
        } else {
            this.subscribeToSaveResponse(this.applicationService.create(this.application));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IApplication>>) {
        result.subscribe((res: HttpResponse<IApplication>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
