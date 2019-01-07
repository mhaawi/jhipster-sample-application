/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ApiDefinationDetailComponent } from 'app/entities/api-defination/api-defination-detail.component';
import { ApiDefination } from 'app/shared/model/api-defination.model';

describe('Component Tests', () => {
    describe('ApiDefination Management Detail Component', () => {
        let comp: ApiDefinationDetailComponent;
        let fixture: ComponentFixture<ApiDefinationDetailComponent>;
        const route = ({ data: of({ apiDefination: new ApiDefination(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ApiDefinationDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ApiDefinationDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ApiDefinationDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.apiDefination).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
