import React from "react";
import ReactDOM from "react-dom";
import { Container, Row, Col, Jumbotron, Card, CardBody } from "reactstrap";
import LoginForm from "./components/loginForm.jsx";
import "bootstrap/dist/css/bootstrap.min.css";
import "./styles.css";

function App() {
  return (
    <div className="App">
      <Container>
        <Row>
          <Col />
          <Col lg="8">
            <Jumbotron>
              <h3>
                Raddiwala.com
              </h3>
              <hr />
              <Card>
                <CardBody>
                  <LoginForm />
                </CardBody>
              </Card>
            </Jumbotron>
          </Col>
          <Col />
        </Row>
      </Container>
    </div>
  );
}

ReactDOM.render(<App />, document.getElementById("root"));
