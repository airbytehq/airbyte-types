
# airbyte-types

## Overview
This package is responsible for generating the types used across Airbyte's projects.

It defines the types in yaml and then uses

- [jsonschema2pojo](https://github.com/joelittlejohn/jsonschema2pojo) to generate the java types
- [datamodel-code-generator](https://github.com/koxudaxi/datamodel-code-generator) to generate the python types

## Libraries
### Java
The `airbyte-types` library is published to [mycloudrepo](https://airbyte.mycloudrepo.io/public/repositories/airbyte-public-jars/io/airbyte/airbyte-types/).

### Python
*For Production* the `airbyte-types` library is published to [pypi](https://pypi.org/project/airbyte-types/).
*For Testing* the `airbyte-types` library is published to [test.pypi](https://test.pypi.org/project/airbyte-types/).

## Key Files
* `models/src/main/resources/*.yaml` - declares the Airbyte Types (in JSONSchema)
* `io.airbyte.types.models` - this package contains various java helpers for working with the protocol.
* `models/bin/generate-python-classes.sh` - this script generates the python types.

## How to generate the types
### All
```bash
./gradlew :generate
```

### Java
```bash
./gradlew :models:generateJsonSchema2Pojo
```

### Python
```bash
./models/bin/generate-python-classes.sh
```

## How to publish the libraries
### Production Versions
On merge to master a new release PR will be created automatically. You can approve and merge this PR to release a new version.

### Development Versions
Deploys to mycloudrepo and test.pypi automatically when a PR is created.


## Pull Requests Titles Must Conform to Convential Commits Convention
We are leveraging the [Release Please Action](https://github.com/marketplace/actions/release-please-action) to manage our version bumping and releasing.
This action relies on the use of the [convential commits convention](https://www.conventionalcommits.org/en/v1.0.0/) to determine whether to bump major, minor, or patch versions. Since we use squash merging, the only commits we see in our history are the titles of our pull requests. This is why we are following conventional commits for our pull request titling. Your actual commits do not need to follow this convention because they never show up in the git history.

Here is a summary of what Release Please looks for in your pull request title.

> The most important prefixes you should have in mind are:
>
> fix: which represents bug fixes, and correlates to a SemVer patch.
> feat: which represents a new feature, and correlates to a SemVer minor.
> feat!:, or fix!:, refactor!:, etc., which represent a breaking change (indicated by the !) and will result in a SemVer major.

Release Please will create a pull request when it believes there is a potential new version to be released. It compiles a change log based
on the commit history sinced last release. It updates this PR appropriately everytime a PR is merged. If you desire to release a new version, you
would approve and merge this PR.

The PR looks something like this. ![example-release-please-pr](https://github.com/google-github-actions/release-please-action/raw/main/screen.png)
