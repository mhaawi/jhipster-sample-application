/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ApiParamUpdateComponent } from 'app/entities/api-param/api-param-update.component';
import { ApiParamService } from 'app/entities/api-param/api-param.service';
import { ApiParam } from 'app/shared/model/api-param.model';

describe('Component Tests', () => {
    describe('ApiParam Management Update Component', () => {
        let comp: ApiParamUpdateComponent;
        let fixture: ComponentFixture<ApiParamUpdateComponent>;
        let service: ApiParamService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ApiParamUpdateComponent]
            })
                .overrideTemplate(ApiParamUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ApiParamUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ApiParamService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ApiParam(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.apiParam = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ApiParam();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.apiParam = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
