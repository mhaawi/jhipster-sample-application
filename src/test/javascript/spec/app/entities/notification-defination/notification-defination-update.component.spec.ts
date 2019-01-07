/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { NotificationDefinationUpdateComponent } from 'app/entities/notification-defination/notification-defination-update.component';
import { NotificationDefinationService } from 'app/entities/notification-defination/notification-defination.service';
import { NotificationDefination } from 'app/shared/model/notification-defination.model';

describe('Component Tests', () => {
    describe('NotificationDefination Management Update Component', () => {
        let comp: NotificationDefinationUpdateComponent;
        let fixture: ComponentFixture<NotificationDefinationUpdateComponent>;
        let service: NotificationDefinationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [NotificationDefinationUpdateComponent]
            })
                .overrideTemplate(NotificationDefinationUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NotificationDefinationUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NotificationDefinationService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new NotificationDefination(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.notificationDefination = entity;
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
                    const entity = new NotificationDefination();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.notificationDefination = entity;
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
