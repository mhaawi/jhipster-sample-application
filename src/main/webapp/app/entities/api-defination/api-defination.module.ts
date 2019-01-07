import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from 'app/shared';
import {
    ApiDefinationComponent,
    ApiDefinationDetailComponent,
    ApiDefinationUpdateComponent,
    ApiDefinationDeletePopupComponent,
    ApiDefinationDeleteDialogComponent,
    apiDefinationRoute,
    apiDefinationPopupRoute
} from './';

const ENTITY_STATES = [...apiDefinationRoute, ...apiDefinationPopupRoute];

@NgModule({
    imports: [JhipsterSampleApplicationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ApiDefinationComponent,
        ApiDefinationDetailComponent,
        ApiDefinationUpdateComponent,
        ApiDefinationDeleteDialogComponent,
        ApiDefinationDeletePopupComponent
    ],
    entryComponents: [
        ApiDefinationComponent,
        ApiDefinationUpdateComponent,
        ApiDefinationDeleteDialogComponent,
        ApiDefinationDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationApiDefinationModule {}
