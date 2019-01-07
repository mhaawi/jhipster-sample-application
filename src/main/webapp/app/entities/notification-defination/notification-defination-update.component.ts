import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { INotificationDefination } from 'app/shared/model/notification-defination.model';
import { NotificationDefinationService } from './notification-defination.service';
import { IApplication } from 'app/shared/model/application.model';
import { ApplicationService } from 'app/entities/application';
import { IApiDefination } from 'app/shared/model/api-defination.model';
import { ApiDefinationService } from 'app/entities/api-defination';

@Component({
    selector: 'jhi-notification-defination-update',
    templateUrl: './notification-defination-update.component.html'
})
export class NotificationDefinationUpdateComponent implements OnInit {
    notificationDefination: INotificationDefination;
    isSaving: boolean;

    applications: IApplication[];

    apidefinations: IApiDefination[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected notificationDefinationService: NotificationDefinationService,
        protected applicationService: ApplicationService,
        protected apiDefinationService: ApiDefinationService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ notificationDefination }) => {
            this.notificationDefination = notificationDefination;
        });
        this.applicationService.query().subscribe(
            (res: HttpResponse<IApplication[]>) => {
                this.applications = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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
        if (this.notificationDefination.id !== undefined) {
            this.subscribeToSaveResponse(this.notificationDefinationService.update(this.notificationDefination));
        } else {
            this.subscribeToSaveResponse(this.notificationDefinationService.create(this.notificationDefination));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<INotificationDefination>>) {
        result.subscribe(
            (res: HttpResponse<INotificationDefination>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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

    trackApplicationById(index: number, item: IApplication) {
        return item.id;
    }

    trackApiDefinationById(index: number, item: IApiDefination) {
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
