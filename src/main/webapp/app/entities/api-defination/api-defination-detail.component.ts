import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IApiDefination } from 'app/shared/model/api-defination.model';

@Component({
    selector: 'jhi-api-defination-detail',
    templateUrl: './api-defination-detail.component.html'
})
export class ApiDefinationDetailComponent implements OnInit {
    apiDefination: IApiDefination;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ apiDefination }) => {
            this.apiDefination = apiDefination;
        });
    }

    previousState() {
        window.history.back();
    }
}
