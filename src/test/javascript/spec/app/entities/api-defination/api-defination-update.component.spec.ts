/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ApiDefinationUpdateComponent } from 'app/entities/api-defination/api-defination-update.component';
import { ApiDefinationService } from 'app/entities/api-defination/api-defination.service';
import { ApiDefination } from 'app/shared/model/api-defination.model';

describe('Component Tests', () => {
    describe('ApiDefination Management Update Component', () => {
        let comp: ApiDefinationUpdateComponent;
        let fixture: ComponentFixture<ApiDefinationUpdateComponent>;
        let service: ApiDefinationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ApiDefinationUpdateComponent]
            })
                .overrideTemplate(ApiDefinationUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ApiDefinationUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ApiDefinationService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ApiDefination(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.apiDefination = entity;
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
                    const entity = new ApiDefination();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.apiDefination = entity;
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
