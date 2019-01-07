import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISourceType } from 'app/shared/model/source-type.model';

@Component({
    selector: 'jhi-source-type-detail',
    templateUrl: './source-type-detail.component.html'
})
export class SourceTypeDetailComponent implements OnInit {
    sourceType: ISourceType;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sourceType }) => {
            this.sourceType = sourceType;
        });
    }

    previousState() {
        window.history.back();
    }
}
