import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
    SourceTypeComponent,
    SourceTypeDetailComponent,
    SourceTypeUpdateComponent,
    SourceTypeDeletePopupComponent,
    SourceTypeDeleteDialogComponent,
    sourceTypeRoute,
    sourceTypePopupRoute
} from './';

const ENTITY_STATES = [...sourceTypeRoute, ...sourceTypePopupRoute];

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SourceTypeComponent,
        SourceTypeDetailComponent,
        SourceTypeUpdateComponent,
        SourceTypeDeleteDialogComponent,
        SourceTypeDeletePopupComponent
    ],
    entryComponents: [SourceTypeComponent, SourceTypeUpdateComponent, SourceTypeDeleteDialogComponent, SourceTypeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationSourceTypeModule {}
