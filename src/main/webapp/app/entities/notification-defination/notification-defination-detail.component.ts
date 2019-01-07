import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INotificationDefination } from 'app/shared/model/notification-defination.model';

@Component({
    selector: 'jhi-notification-defination-detail',
    templateUrl: './notification-defination-detail.component.html'
})
export class NotificationDefinationDetailComponent implements OnInit {
    notificationDefination: INotificationDefination;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ notificationDefination }) => {
            this.notificationDefination = notificationDefination;
        });
    }

    previousState() {
        window.history.back();
    }
}
