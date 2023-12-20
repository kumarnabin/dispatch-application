import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './master-circuit.reducer';

export const MasterCircuitDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const masterCircuitEntity = useAppSelector(state => state.masterCircuit.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="masterCircuitDetailsHeading">Master Circuit</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{masterCircuitEntity.id}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{masterCircuitEntity.name}</dd>
          <dt>
            <span id="address">Address</span>
          </dt>
          <dd>{masterCircuitEntity.address}</dd>
          <dt>Service Provider</dt>
          <dd>
            {masterCircuitEntity.serviceProviders
              ? masterCircuitEntity.serviceProviders.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {masterCircuitEntity.serviceProviders && i === masterCircuitEntity.serviceProviders.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/master-circuit" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/master-circuit/${masterCircuitEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default MasterCircuitDetail;
