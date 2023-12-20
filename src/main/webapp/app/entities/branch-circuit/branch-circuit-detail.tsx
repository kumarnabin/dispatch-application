import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './branch-circuit.reducer';

export const BranchCircuitDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const branchCircuitEntity = useAppSelector(state => state.branchCircuit.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="branchCircuitDetailsHeading">Branch Circuit</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{branchCircuitEntity.id}</dd>
          <dt>
            <span id="title">Title</span>
          </dt>
          <dd>{branchCircuitEntity.title}</dd>
          <dt>
            <span id="publicationDate">Publication Date</span>
          </dt>
          <dd>
            {branchCircuitEntity.publicationDate ? (
              <TextFormat value={branchCircuitEntity.publicationDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>Master Circuit</dt>
          <dd>{branchCircuitEntity.masterCircuit ? branchCircuitEntity.masterCircuit.id : ''}</dd>
          <dt>Branch</dt>
          <dd>
            {branchCircuitEntity.branches
              ? branchCircuitEntity.branches.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {branchCircuitEntity.branches && i === branchCircuitEntity.branches.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/branch-circuit" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/branch-circuit/${branchCircuitEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default BranchCircuitDetail;
