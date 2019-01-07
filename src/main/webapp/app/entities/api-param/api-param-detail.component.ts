import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IApiParam } from 'app/shared/model/api-param.model';

@Component({
    selector: 'jhi-api-param-detail',
    templateUrl: './api-param-detail.component.html'
})
export class ApiParamDetailComponent implements OnInit {
    apiParam: IApiParam;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ apiParam }) => {
            this.apiParam = apiParam;
        });
    }

    previousState() {
        window.history.back();
    }
}
