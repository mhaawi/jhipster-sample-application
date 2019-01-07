/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { SourceTypeDetailComponent } from 'app/entities/source-type/source-type-detail.component';
import { SourceType } from 'app/shared/model/source-type.model';

describe('Component Tests', () => {
    describe('SourceType Management Detail Component', () => {
        let comp: SourceTypeDetailComponent;
        let fixture: ComponentFixture<SourceTypeDetailComponent>;
        const route = ({ data: of({ sourceType: new SourceType(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [SourceTypeDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SourceTypeDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SourceTypeDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sourceType).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
