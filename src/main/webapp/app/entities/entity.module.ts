import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterSampleApplicationApplicationModule } from './application/application.module';
import { JhipsterSampleApplicationNotificationDefinationModule } from './notification-defination/notification-defination.module';
import { JhipsterSampleApplicationApiDefinationModule } from './api-defination/api-defination.module';
import { JhipsterSampleApplicationApiParamModule } from './api-param/api-param.module';
import { JhipsterSampleApplicationSourceTypeModule } from './source-type/source-type.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        JhipsterSampleApplicationApplicationModule,
        JhipsterSampleApplicationNotificationDefinationModule,
        JhipsterSampleApplicationApiDefinationModule,
        JhipsterSampleApplicationApiParamModule,
        JhipsterSampleApplicationSourceTypeModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationEntityModule {}
