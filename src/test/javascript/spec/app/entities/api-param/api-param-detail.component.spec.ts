/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ApiParamDetailComponent } from 'app/entities/api-param/api-param-detail.component';
import { ApiParam } from 'app/shared/model/api-param.model';

describe('Component Tests', () => {
    describe('ApiParam Management Detail Component', () => {
        let comp: ApiParamDetailComponent;
        let fixture: ComponentFixture<ApiParamDetailComponent>;
        const route = ({ data: of({ apiParam: new ApiParam(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ApiParamDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ApiParamDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ApiParamDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.apiParam).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
