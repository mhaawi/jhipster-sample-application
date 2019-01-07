/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { NotificationDefinationDetailComponent } from 'app/entities/notification-defination/notification-defination-detail.component';
import { NotificationDefination } from 'app/shared/model/notification-defination.model';

describe('Component Tests', () => {
    describe('NotificationDefination Management Detail Component', () => {
        let comp: NotificationDefinationDetailComponent;
        let fixture: ComponentFixture<NotificationDefinationDetailComponent>;
        const route = ({ data: of({ notificationDefination: new NotificationDefination(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [NotificationDefinationDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(NotificationDefinationDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NotificationDefinationDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.notificationDefination).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
