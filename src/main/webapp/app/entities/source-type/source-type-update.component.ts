import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ISourceType } from 'app/shared/model/source-type.model';
import { SourceTypeService } from './source-type.service';

@Component({
    selector: 'jhi-source-type-update',
    templateUrl: './source-type-update.component.html'
})
export class SourceTypeUpdateComponent implements OnInit {
    sourceType: ISourceType;
    isSaving: boolean;

    constructor(protected sourceTypeService: SourceTypeService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sourceType }) => {
            this.sourceType = sourceType;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.sourceType.id !== undefined) {
            this.subscribeToSaveResponse(this.sourceTypeService.update(this.sourceType));
        } else {
            this.subscribeToSaveResponse(this.sourceTypeService.create(this.sourceType));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISourceType>>) {
        result.subscribe((res: HttpResponse<ISourceType>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
