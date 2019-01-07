import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IApiDefination } from 'app/shared/model/api-defination.model';
import { ApiDefinationService } from './api-defination.service';

@Component({
    selector: 'jhi-api-defination-delete-dialog',
    templateUrl: './api-defination-delete-dialog.component.html'
})
export class ApiDefinationDeleteDialogComponent {
    apiDefination: IApiDefination;

    constructor(
        protected apiDefinationService: ApiDefinationService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.apiDefinationService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'apiDefinationListModification',
                content: 'Deleted an apiDefination'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-api-defination-delete-popup',
    template: ''
})
export class ApiDefinationDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ apiDefination }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ApiDefinationDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.apiDefination = apiDefination;
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
