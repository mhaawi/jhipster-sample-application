/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { NotificationDefinationService } from 'app/entities/notification-defination/notification-defination.service';
import { INotificationDefination, NotificationDefination, Channel } from 'app/shared/model/notification-defination.model';

describe('Service Tests', () => {
    describe('NotificationDefination Service', () => {
        let injector: TestBed;
        let service: NotificationDefinationService;
        let httpMock: HttpTestingController;
        let elemDefault: INotificationDefination;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(NotificationDefinationService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new NotificationDefination(0, 'AAAAAAA', Channel.EMAIL, 'AAAAAAA', false, 'AAAAAAA');
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a NotificationDefination', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new NotificationDefination(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a NotificationDefination', async () => {
                const returnedFromService = Object.assign(
                    {
                        notificationKey: 'BBBBBB',
                        channelName: 'BBBBBB',
                        templatePath: 'BBBBBB',
                        isActive: true,
                        sourceType: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of NotificationDefination', async () => {
                const returnedFromService = Object.assign(
                    {
                        notificationKey: 'BBBBBB',
                        channelName: 'BBBBBB',
                        templatePath: 'BBBBBB',
                        isActive: true,
                        sourceType: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a NotificationDefination', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
