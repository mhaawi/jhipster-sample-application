/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { NotificationDefinationDeleteDialogComponent } from 'app/entities/notification-defination/notification-defination-delete-dialog.component';
import { NotificationDefinationService } from 'app/entities/notification-defination/notification-defination.service';

describe('Component Tests', () => {
    describe('NotificationDefination Management Delete Component', () => {
        let comp: NotificationDefinationDeleteDialogComponent;
        let fixture: ComponentFixture<NotificationDefinationDeleteDialogComponent>;
        let service: NotificationDefinationService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [NotificationDefinationDeleteDialogComponent]
            })
                .overrideTemplate(NotificationDefinationDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NotificationDefinationDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NotificationDefinationService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
