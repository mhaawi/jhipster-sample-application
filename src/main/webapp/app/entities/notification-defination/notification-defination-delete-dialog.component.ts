import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INotificationDefination } from 'app/shared/model/notification-defination.model';
import { NotificationDefinationService } from './notification-defination.service';

@Component({
    selector: 'jhi-notification-defination-delete-dialog',
    templateUrl: './notification-defination-delete-dialog.component.html'
})
export class NotificationDefinationDeleteDialogComponent {
    notificationDefination: INotificationDefination;

    constructor(
        protected notificationDefinationService: NotificationDefinationService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.notificationDefinationService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'notificationDefinationListModification',
                content: 'Deleted an notificationDefination'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-notification-defination-delete-popup',
    template: ''
})
export class NotificationDefinationDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ notificationDefination }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NotificationDefinationDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.notificationDefination = notificationDefination;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
